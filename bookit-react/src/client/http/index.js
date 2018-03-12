import axios from 'axios'
import { fromJS } from 'immutable'

import books from './books'
import security from './security'
import storage from '../storage'

/**
 * Retrieves token from local storage if any
 *
 * @return an auth token if it has been stored in local storage
 */
const getToken = () => {
    const login = storage.get('login')

    return login ? login.token : null
}

/**
 * Default http client
 */
export const client = axios.create({
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': `JWT ${getToken()}`
    },
    baseURL: 'http://localhost:8888/graphql',
    transformResponse: [ (data) => {
        return fromJS(JSON.parse(data))
    }]
})

/**
 * Exports all available http clients
 */
export default {
    books: books(client),
    security: security(client)
}
