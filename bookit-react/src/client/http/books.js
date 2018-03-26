import { parseError } from './utils'
import storage from '../storage'

export default (client) => ({
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
        const data = {
            query,
            variables: {
                clientMutationId: 'list-books',
                offset: 1,
                maxRows: 20
            }
        }

        return client
            .post('', data)
            .then(resp => resp.data.getIn(['data', 'books']))
            .catch(parseError)
    }
})
