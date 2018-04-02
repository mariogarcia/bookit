import { parseError } from './utils'

export default (client) => ({
    stats () {
        const query = `
        {
          stats {
            books
            authors
          }
        }
        `
        const data = {
            query,
            variables: {
              clientMutationId: 'stats',
            }
        }

        return client
            .post('', data)
            .then(resp => resp.data.getIn(['data', 'stats']))
            .catch(parseError)
    }
})
