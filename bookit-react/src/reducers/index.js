import booksReducers from './books'
import securityReducers from './security'
import uiReducers from './ui'
import { combineReducers } from 'redux'

export default combineReducers({
    books: booksReducers,
    security: securityReducers,
    ui: uiReducers
})
