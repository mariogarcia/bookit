import booksReducers from './books'
import securityReducers from './security'
import uiReducers from './ui'
import dashboardReducers from './dashboard'
import { combineReducers } from 'redux'

export default combineReducers({
    books: booksReducers,
    security: securityReducers,
    ui: uiReducers,
    dashboard: dashboardReducers
})
