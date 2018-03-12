import { takeLatest, call, put } from 'redux-saga/effects'
import { actionCreators, actionTypes } from '../reducers/security'
import http from '../client/http'
import storage from '../client/storage'
import { push } from 'react-router-redux'
/**
 * Handles action involved on login
 *
 * @param credentials required credentials to login
 */
export function* login(credentials) {
    try {
        const apiLogin = yield call(http.security.login, credentials)
        const storedLogin = yield call(storage.set, 'login', apiLogin)

        yield put(push('/books'))
        yield put(actionCreators.loginSuccess(storedLogin))
    } catch (err) {
        const error = err.code || err
        yield put(actionCreators.loginFailure(error))
        yield put(push('/login'))
    }
}

export default [
    takeLatest(actionTypes.LOGIN.REQUEST, login)
]
