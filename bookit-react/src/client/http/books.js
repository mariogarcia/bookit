import { parseError } from './utils'

export default client => ({
    list () {
        const query = `
        query BookList($offset: Int, $maxRows: Int) {
          books(offset: $offset, maxRows: $maxRows) {
            title
            shortDescription
            imageUri
          }
        }
        `
        const variables = {
            clientMutationId: 'list-books',
            offset: 1,
            maxRows: 20
        }

        return client
            .post('', { query, variables })
            .then(resp => resp.data.getIn(['data', 'books']))
            .catch(parseError)
    }
})
