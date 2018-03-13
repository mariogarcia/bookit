import React from 'react'
import yup from 'yup'
import Form from 'react-formal'

/**
 * Validation rules for login form
 *
 * @since 0.1.0
 */
const validation = yup.object({
    username: yup.string().required('Username is required'),
    password: yup.string().required('Password is required')
})

/**
 * Renders a login form
 *
 * @since 0.1.0
 */
export class LoginForm extends React.Component {

    /**
     * Handles form submit once the validation has been passed
     * successfully
     *
     * @param formValue a map with values gathered from form
     * @since 0.1.0
     */
    handleSubmit (formValue) {
        this.props.onSubmit(formValue)
    }

    render () {
        return (
            <Form
                schema={validation}
                onSubmit={(formValue) => this.handleSubmit(formValue)}>
                <div className="form-group">
                    <label>Email Address</label>
                    <Form.Field
                        name='username'
                        className='form-control'
                        placeholder='Username' />
                    <Form.Message for='username'/>
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <Form.Field
                        name='password'
                        type='password'
                        className='form-control'
                        placeholder='Password' />
                    <Form.Message for='password'/>
                </div>
                <Form.Button
                    className="btn btn-primary btn-flat m-b-30 m-t-30"
                    type='submit'>Sign in</Form.Button>
            </Form>
        )
    }
}
