import React from 'react'

export class Header extends React.Component {
    render () {
        return (
            <div className="header">
                <nav className="navbar top-navbar navbar-expand-md navbar-light">
                    <div className="navbar-header">
                        <a className="navbar-brand" href="index.html">
                            <b><img src="https://colorlib.com/polygon/elaadmin/images/logo.png" alt="homepage" className="dark-logo" /></b>
                            <span><img src="https://colorlib.com/polygon/elaadmin/images/logo-text.png" alt="homepage" className="dark-logo" /></span>
                        </a>
                    </div>
                    <div className="navbar-collapse">
                        <ul className="navbar-nav mr-auto mt-md-0">
                            <li className="nav-item">
                                <a className="nav-link nav-toggler hidden-md-up text-muted  " href="javascript:void(0)">
                                    <i className="mdi mdi-menu"></i>
                                </a>
                            </li>
                            <li className="nav-item m-l-10"> <a className="nav-link sidebartoggler hidden-sm-down text-muted  " href="javascript:void(0)"><i className="ti-menu"></i></a> </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }

}
