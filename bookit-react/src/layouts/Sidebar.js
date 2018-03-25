import React from 'react'
import { Link } from 'react-router-dom'

export class Sidebar extends React.Component {
    render () {
        return (
            <div className="left-sidebar">
                <div className="slimScrollDiv">
                    <div className="scroll-sidebar">
                        <nav className="sidebar-nav">
                            <ul id="sidebarnav">
                                <li className="nav-devider"></li>
                                <li className="nav-label">Home</li>
                                <li>
                                    <Link to='/' aria-expanded={false}><i className="fa fa-tachometer"></i>
                                        <span className="hide-menu">Dashboard </span>
                                    </Link>
                                </li>
                                <li>
                                    <Link to='/languages' aria-expanded={false}><i className="fa fa-github"></i>
                                        <span className="hide-menu">Languages </span>
                                    </Link>
                                </li>
                                <li>
                                    <Link to='/authors' aria-expanded={false}><i className="fa fa-users"></i>
                                        <span className="hide-menu">Authors </span>
                                    </Link>
                                </li>
                                <li>
                                    <Link to='/books' aria-expanded={false}><i className="fa fa-book"></i>
                                        <span className="hide-menu">Books </span>
                                    </Link>
                                </li>
                                <li>
                                    <Link to='/bartolos' aria-expanded={false}><i className="fa fa-reddit-alien"></i>
                                        <span className="hide-menu">Bartolos </span>
                                    </Link>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        )
    }
}
