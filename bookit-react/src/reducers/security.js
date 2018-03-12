import { Map } from 'immutable'

/**
 * Possible types of action
 */
export const actionTypes = {
    LOGIN: {
        REQUEST: '@bookit/LOGIN/REQUEST',
        SUCCESS: '@bookit/LOGIN/SUCCESS',
        FAILURE: '@bookit/LOGIN/FAILURE'
    }
}

/**
 * Initial login reducer state
 */
export const initialState = Map({
  isLoading: false,
  error: null,
  login: Map()
})

/**
 * Reducers related to login management
 */
const loginReducer = (state = initialState, action) => {
    switch(action.type) {
    case actionTypes.LOGIN.REQUEST:
        return state
            .set('isLoading', true)

    case actionTypes.LOGIN.SUCCESS:
        return state
            .set('isLoading', false)
            .set('login', action.login)

    case actionTypes.LOGIN.FAILURE:
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
    login: (credentials) => {
        return {
            type: actionTypes.LOGIN.REQUEST,
            credentials
        }
    },
    loginSuccess: (login) => {
        return {
            type: actionTypes.LOGIN.SUCCESS,
            login
        }
    },
    loginFailure: (error) => {
        return {
            type: actionTypes.LOGIN.FAILURE,
            error
        }
    }
}

export default loginReducer
