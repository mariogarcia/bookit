/**
 * @param allowedRoles
 * @param WrappedComponent
 * @return
 * @since 0.1.0
 */
export const Authorization = allowedRoles => WrappedComponent =>
  return class WithAuthorization extends React.Component {
    constructor(props) {
      super(props)
    }

    render() {
        const { role } = this.state.user

      if (allowedRoles.includes(role)) {
        return <WrappedComponent {...this.props} />
      } else {
        return <h1>No page for you!</h1>
      }
    }
  }

/**
 * @since 0.1.0
 */
export const User = Authorization(['USER'])

/**
 * @since 0.1.0
 */
export const Admin = Authorization(['ADMIN'])
