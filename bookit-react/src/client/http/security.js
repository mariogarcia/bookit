import { parseError } from './utils'

export default client => ({
    login ({username, password}) {
        const query = `
        query Login($credentials: Credentials!) {
          login(credentials: $credentials) {
            token
            name
            roles
          }
        }
        `
        const variables = {
            clientMutationId: 'login',
            credentials: {
                username,
                password
            }
        }

        return client
            .post('', { query, variables })
            .then(resp => {
                const login = resp.data.getIn(['data', 'login'])
                const token = login.get('token')

                client.defaults.headers.common['Authorization'] = `JWT ${token}`;

                return login
            })
            .catch(parseError)
    }
})
