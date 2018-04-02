import { List, Map } from 'immutable'

/**
 * Possible types of action
 */
export const actionTypes = {
    DASHBOARD: {
        STATS_REQUEST: '@bookit/DASHBOARD/STATS_REQUEST',
        STATS_SUCCESS: '@bookit/DASHBOARD/STATS_SUCCESS',
        STATS_FAILURE: '@bookit/DASHBOARD/STATS_FAILURE'
    }
}

/**
 * Initial book reducer state
 */
export const initialState = Map({
  isLoading: false,
  error: null,
  stats: {
    books: 0,
    authors: 0,
    languages: 0,
    bartolos: 0
  }
})

/**
 * Reducers related to book management
 */
const dashboardReducers = (state = initialState, action) => {
    switch(action.type) {
      case actionTypes.DASHBOARD.STATS_REQUEST:
        return state.set('isLoading', true)

      case actionTypes.DASHBOARD.STATS_SUCCESS:
        return state
            .set('isLoading', false)
            .set('stats', action.stats)

      case actionTypes.DASHBOARD.STATS_FAILURE:
        return state
            .set('isLoading', false)
            .set('stats', action.error)

      default:
        return state
    }
}

export const actionCreators = {
    loadStats: () => {
        return {
            type: actionTypes.DASHBOARD.STATS_REQUEST
        }
    },
    statsSuccess: (stats) => {
        return {
            type: actionTypes.DASHBOARD.STATS_SUCCESS,
            stats: stats
        }
    },
    statsFailure: (error) => {
        return {
            type: actionTypes.DASHBOARD.STATS_FAILURE,
            error: error
        }
    },
}

export const selectors = {
    getStats: (state) => ({
        books: state.dashboard.getIn(['stats', 'books']) || 0,
        authors: state.dashboard.getIn(['stats', 'authors']) || 0,
        languages: state.dashboard.getIn(['stats', 'languages']) || 0,
        bartolos: state.dashboard.getIn(['stats', 'bartolos']) || 0,
    })

}

export default dashboardReducers
