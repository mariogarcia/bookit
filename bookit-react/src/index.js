import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import createHistory from 'history/createBrowserHistory'
import { ConnectedRouter, routerMiddleware as createRouterMiddleware } from 'react-router-redux'
import rootReducer from './reducers'
import registerServiceWorker from './registerServiceWorker'
import createSagaMiddleware from 'redux-saga'
import rootSaga from './sagas'

/**
 * Sagas middleware
 */
const sagaMiddleware = createSagaMiddleware()

/**
 * Router middleware
 */
const history = createHistory()
const routerMiddleare = createRouterMiddleware(history)

/**
 * Create store
 */
const store = createStore(
    rootReducer,
    applyMiddleware(sagaMiddleware, routerMiddleare))

/**
 * Init sagas BEFORE! rendering app
 */
sagaMiddleware.run(rootSaga)

/**
 * Bootstrap app
 */
ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <App />
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
)

/**
 * For development purposes
 */
registerServiceWorker()
