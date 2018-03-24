import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'

/**
 * @since 0.1.0
 */
const Auth = (LoginPage) => (token) => (WrappedComponent) => {
    return function(props) {
        if (token) {
            return (<WrappedComponent {...props} />)
        } else {
            return (<Redirect to="/login" />)
        }
    }
}

export default Auth
