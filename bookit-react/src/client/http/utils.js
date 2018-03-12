import { List, Map } from 'immutable'

export const parseError = (error) => {
    return Map({
        errors: List([{
            message: error.message
        }]),
        data: null
    })
}
