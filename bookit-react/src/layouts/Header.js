import React from 'react'

import logo from './images/logo.png'
import defaultAvatar from './images/avatar/6.jpg'
import logoText from './images/logo-text.png'

export class Header extends React.Component {

    menuToggle (ev) {
        ev.preventDefault()
        console.log(this.props)
        this.props.showMenu(!this.props.menuVisible)
    }

    render () {
        const logoTextStyle = this.props.menuVisible ?
              {} :
              { display: 'none' }

        return (
            <div className="header">
                <nav className="navbar top-navbar navbar-expand-md navbar-light">
                    <div className="navbar-header">
                        <a className="navbar-brand" href="index.html">
                            <b><img src={logo} alt="homepage" className="dark-logo" /></b>
                            <span style={logoTextStyle}><img src={logoText} alt="homepage" className="dark-logo" /></span>
                        </a>
                    </div>
                    <div className="navbar-collapse">
                        <ul className="navbar-nav mr-auto mt-md-0">
                            <li className="nav-item">
                                <a className="nav-link nav-toggler hidden-md-up text-muted  " href="javascript:void(0)">
                                    <i className="mdi mdi-menu"></i>
                                </a>
                            </li>
                            <li className="nav-item m-l-10">
                                <a className="nav-link sidebartoggler hidden-sm-down text-muted  " onClick={(ev) => this.menuToggle(ev)} >
                                    <i className="ti-menu"></i>
                                </a>
                            </li>
                        </ul>
                        <ul className="navbar-nav my-lg-0">
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle text-muted  "
                                   href="#"
                                   data-toggle="dropdown"
                                   aria-haspopup="true"
                                   aria-expanded="false">
                                    <img src={defaultAvatar} alt="user" className="profile-pic" />
                                </a>
                                <div className="dropdown-menu dropdown-menu-right animated zoomIn">
                                    <ul className="dropdown-user">
                                        <li><a href="#"><i className="ti-user"></i> Profile</a></li>
                                        <li><a href="#"><i className="ti-wallet"></i> Balance</a></li>
                                        <li><a href="#"><i className="ti-email"></i> Inbox</a></li>
                                        <li><a href="#"><i className="ti-settings"></i> Setting</a></li>
                                        <li><a href="#"><i className="fa fa-power-off"></i> Logout</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }

}
