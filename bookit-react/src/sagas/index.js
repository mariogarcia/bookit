import { all } from 'redux-saga/effects'

import booksSagas from './books'
import securitySagas from './security'
import dashboardSagas from './dashboard'

export default function* rootSaga () {
    yield all([
        ...booksSagas,
        ...securitySagas,
        ...dashboardSagas
    ])
}
