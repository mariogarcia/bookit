import React from 'react';

import { Header } from './Header'
import { Sidebar } from './Sidebar'

import './MainLayout.css'
import './style.css'
import './helper.css'

export class MainLayout extends React.Component {
    render () {
        return (
            <div id="main-wrapper">
                <Header />
                <Sidebar />
                {this.props.children}
            </div>
        )
    }
}
