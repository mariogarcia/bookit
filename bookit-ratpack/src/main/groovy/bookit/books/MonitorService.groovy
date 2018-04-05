package bookit.books

import javax.inject.Inject
import ratpack.service.Service
import ratpack.service.StopEvent
import ratpack.service.StartEvent
import groovy.util.logging.Slf4j
import org.neo4j.driver.v1.Session
import org.apache.commons.io.monitor.FileAlterationListener
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import org.apache.commons.io.monitor.FileAlterationMonitor
import org.apache.commons.io.monitor.FileAlterationObserver

@Slf4j
class MonitorService implements Service {

  @Inject
  Session session

  FileAlterationMonitor monitor = new FileAlterationMonitor(1000)

  @Override
  void onStart(StartEvent event) {
    log.info("Starting bartolo")
    FileAlterationObserver observer = new FileAlterationObserver("/tmp/bartolos")
    FileAlterationListener listener = new FileAlterationListenerAdaptor() {
      @Override
      void onFileCreate(File file) {
        file.eachLine(this::processLine)
      }
    }

    observer.addListener(listener)
    monitor.addObserver(observer)
    monitor.start()
  }

  void processLine(String row) {
    Map book = processRow(row)

    for (String author: book.authors) {
      log.info "processing ${book.title}:${author}"
      session.run(insertionQuery, merge(book, author))
    }
  }

  Map<String,Object> merge(Map book, String author) {
    return (book + [author: author, when: book.year]) as Map<String, Object>
  }

  String getInsertionQuery() {
    return '''
      MERGE (tech:Technology {name: {technology}})
      MERGE (book:Book {title: {title}, isbn: {isbn}, thumbnail: {thumbnail}, month: {month}, year: {year}})
      MERGE (tech)-[:HAS_BOOK]->(book)
      MERGE (author:Author {name: {author}})
      MERGE (author)-[:WROTE {when: {when}}]->(book)
    '''
  }

  Map processRow(String row) {
    String[] values = row.split('\\|')

    return [
      title: values[0],
      technology: values[1],
      isbn: values[2],
      thumbnail: values[3],
      month: values[4],
      year: values[5],
      authors: values[6, -1]
    ]
  }

  @Override
  void onStop(StopEvent event) {
    log.info("Stopping bartolo")
    monitor.stop()
  }
}
