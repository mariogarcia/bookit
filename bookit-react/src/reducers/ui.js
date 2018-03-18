import { Map } from 'immutable'

/**
 * Possible types of action
 */
export const actionTypes = {
    UI: {
        MENU: {
            SHOW: '@bookit/UI/MENU/SHOW'
        }
    }
}

/**
 * Initial ui reducer state
 */
export const initialState = Map({
    menuVisible: true
})

/**
 * Reducers related to ui management
 */
const uiReducer = (state = initialState, action) => {
    switch(action.type) {
      case actionTypes.UI.MENU.SHOW:
        return state
            .set('menuVisible', action.menuVisible)

      default:
        return state
    }
}

/**
 * Possible actions used throughout the application
 */
export const actionCreators = {
    showMenu: (menuVisible) => {
        return {
            type: actionTypes.UI.MENU.SHOW,
            menuVisible
        }
    }
}

export default uiReducer
