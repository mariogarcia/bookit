import React from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { BookList } from './BookList'
import { Page, Content } from '../../components/page'
import { actionCreators as booksActionCreators } from '../../reducers/books'
import MainLayout from '../../layouts/MainLayout'

import './BookListPage.css'

/**
 * Shows a list of books
 *
 * @since 0.1.0
 */
const BookListPage = (props) => (
    <MainLayout>
        <Page title='Books'>
            <Content>
                <BookList
                    books={props.books}
                    listBooks={props.list}/>
            </Content>
        </Page>
    </MainLayout>
)

const mapDispatchToProps = (dispatch) => ({
  ...bindActionCreators(booksActionCreators, dispatch),
})

const mapStateToProps = (state) => {
  return {
      books: state.books.get('books')
  }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BookListPage)
