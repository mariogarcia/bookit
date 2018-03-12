import React from 'react'
import { Link } from 'react-router-dom'

export class Sidebar extends React.Component {
    render () {
        return (
            <div className="left-sidebar">
                <div className="scroll-sidebar">
                    <nav className="sidebar-nav">
                        <ul id="sidebarnav">
                            <li className="nav-devider"></li>
                            <li className="nav-label">Home</li>
                            <li>
                                <Link to='/books' aria-expanded={false}><i className="fa fa-book"></i>Books </Link>
                            </li>
                            <li>
                                <Link to='/links' aria-expanded={false}><i className="fa fa-link"></i>Links </Link>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        )
    }
}
