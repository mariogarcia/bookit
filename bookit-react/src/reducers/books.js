import { List, Map } from 'immutable'

/**
 * Possible types of action
 */
export const actionTypes = {
    BOOKS: {
        LIST_REQUEST: '@bookit/BOOKS/LIST_REQUEST',
        LIST_SUCCESS: '@bookit/BOOKS/LIST_SUCCESS',
        LIST_FAILURE: '@bookit/BOOKS/LIST_FAILURE'
    }
}

/**
 * Initial book reducer state
 */
export const initialState = Map({
  isLoading: false,
  error: null,
  books: List()
})

/**
 * Reducers related to book management
 */
const bookReducer = (state = initialState, action) => {
    switch(action.type) {
    case actionTypes.BOOKS.LIST_REQUEST:
        return state
            .set('isLoading', true)

    case actionTypes.BOOKS.LIST_SUCCESS:
        return state
            .set('isLoading', false)
            .set('books', action.books)

    case actionTypes.BOOKS.LIST_FAILURE:
        return state
            .set('isLoading', false)
            .set('error', action.error)

    default:
        return state
    }
}

/**
 * Possible actions used throughout the application
 */
export const actionCreators = {
    list: () => {
        return {
            type: actionTypes.BOOKS.LIST_REQUEST
        }
    },
    listSuccess: (books) => {
        return {
            type: actionTypes.BOOKS.LIST_SUCCESS,
            books
        }
    },
    listFailure: (error) => {
        return {
            type: actionTypes.BOOKS.LIST_FAILURE,
            error
        }
    }
}

export default bookReducer
