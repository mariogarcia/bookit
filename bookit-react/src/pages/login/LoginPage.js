import React from 'react'
import { Map } from 'immutable'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { LoginLayout } from '../../layouts/LoginLayout'
import { LoginForm } from './LoginForm'
import { actionCreators as loginActionCreators } from '../../reducers/security'

import './LoginPage.css'

const LoginPage = (props) => (
    <LoginLayout>
        <div className="login-form">
            <h4>Login</h4>
            <LoginForm
                error={props.error}
                onSubmit={props.login} />
        </div>
    </LoginLayout>
)

const mapStateToProps = (state) => {
    return {
        login: state.security.get('login'),
        error: state.security.get('error')
    }
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators(loginActionCreators, dispatch)
})

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginPage)
