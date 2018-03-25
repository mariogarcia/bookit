import axios from 'axios'
import { fromJS } from 'immutable'

import books from './books'
import security from './security'
import storage from '../storage'

/**
 * Default http client. Authorization header is calling
 * getToken(). That will only work if the user was already
 * successfully authenticated and there is still the login information
 * in the local storage.
 */
export const client = axios.create({
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    baseURL: 'http://localhost:8888/graphql',
    transformResponse: [ (data) => {
        return fromJS(JSON.parse(data))
    }]
})

const createAuthHeaders = (extraHeaders = {}) => {
    const token = storage.get('login').token

    return {
        headers: {
            ...extraHeaders,
            Authorization: `JWT ${token}`
        }
    }
}

/**
 * Exports all available http clients
 */
export default {
    books: books(client, createAuthHeaders),
    security: security(client)
}
