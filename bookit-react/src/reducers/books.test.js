import reducer, { actionCreators } from './books'
import { Map, List } from 'immutable'

describe('Reducers/Books', () => {
    it('should return the initial state', () => {
        const received = reducer(undefined,{})
        const expected = Map({
            isLoading: false,
            error: null,
            books: List()
        })

        expect(received).toEqual(expected)
    })

    it('should return in progress while getting books', () => {
        const received = reducer(undefined, actionCreators.list())
        const expected = Map({
            isLoading: true,
            error: null,
            books: List()
        })

        expect(received).toEqual(expected)
    })

    it('should return books when success', () => {
        const books = List([])
        const received = reducer(undefined, actionCreators.listSuccess(books))
        const expected = Map({
            isLoading: false,
            error: null,
            books: books
        })

        expect(received).toEqual(expected)
    })

    it('should return an error message when something goes wrong', () => {
        const received = reducer(undefined, actionCreators.listFailure('upps'))
        const expected = Map({
            isLoading: false,
            error: 'upps',
            books: List()
        })

        expect(received).toEqual(expected)
    })
})
