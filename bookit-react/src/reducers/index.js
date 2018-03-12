import booksReducers from './books'
import securityReducers from './security'
import { combineReducers } from 'redux'

export default combineReducers({
    books: booksReducers,
    security: securityReducers
})
