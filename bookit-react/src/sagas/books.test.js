import React from 'react';

import { list } from './books'
import { call, put } from 'redux-saga/effects'
import { actionTypes } from '../reducers/books'

import http from '../client/http'

jest.mock('../client/http')

describe('Sagas/Books: list books', () => {
    const generator = list()

    it('should get data from http', () => {
        const next = generator.next().value

        expect(next).toEqual(call(http.books.list))
    })

    it('should put success for book list', () => {
        const next = generator.next().value

        expect(next).toEqual(put({type: actionTypes.BOOKS.LIST_SUCCESS}))
    })

    it('should put failure for book list', () => {
        const error = 'Error message'
        const next = generator.throw(error).value

        expect(next).toEqual(put({type: actionTypes.BOOKS.LIST_FAILURE, error}))
    })
})
