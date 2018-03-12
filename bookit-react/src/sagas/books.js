import { put, call, takeLatest } from 'redux-saga/effects'
import { actionCreators, actionTypes } from '../reducers/books'
import http from '../client/http'

/**
 * Lists all available books
 *
 * @since 0.1.0
 */
export function* list() {
    try {
        const books = yield call(http.books.list)
        yield put(actionCreators.listSuccess(books))
    } catch (err) {
        const error = err.code || err
        yield put(actionCreators.listFailure(error))
    }
}


export default [
  takeLatest(actionTypes.BOOKS.LIST_REQUEST, list)
]
