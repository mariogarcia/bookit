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

const ok = (config) => {
    const login = storage.get('login')
    let token

    if (login) {
        token = login.token
    }

    return {
        ...config,
        headers: {
            Authorization: `JWT ${token}`
        }
    }
}

const ko = (err) => {
    return Promise.reject(err)
}

client.interceptors.request.use(ok, ko)

/**
 * Exports all available http clients
 */
export default {
    books: books(client),
    security: security(client)
}
