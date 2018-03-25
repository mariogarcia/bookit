import React from 'react';

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { Header } from './Header'
import { Sidebar } from './Sidebar'
import { actionCreators as uiActionCreators } from '../reducers/ui'

import './bootstrap.min.css'
import './style.css'
import './helper.css'


class MainLayout extends React.Component {

    toggleSidebar (visible) {
        if (visible) {
            return 'fix-header'
        }

        return 'fix-header mini-sidebar'
    }

    render () {
        const sidebarStyle = this.toggleSidebar(this.props.menuVisible)

        return (
            <div className={sidebarStyle}>
                <div id="main-wrapper">
                    <Header
                        menuVisible={this.props.menuVisible}
                        showMenu={this.props.showMenu} />
                    <Sidebar />
                    {this.props.children}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
  ...bindActionCreators(uiActionCreators, dispatch),
})

const mapStateToProps = (state) => {
  return {
      menuVisible: state.ui.get('menuVisible')
  }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(MainLayout)
