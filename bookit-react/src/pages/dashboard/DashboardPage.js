import React from 'react'
// import { connect } from 'react-redux'
// import { bindActionCreators } from 'redux'
//import { BookList } from './BookList'
import { Page, Content } from '../../components/page'
//import { actionCreators as booksActionCreators } from '../../reducers/books'
import MainLayout from '../../layouts/MainLayout'
import defaultAvatar from '../../layouts/images/avatar/6.jpg'

import './DashboardPage.css'

/**
 *
 * @since 0.1.0
 */
const DashboardPage = (props) => (
    <MainLayout>
        <Page title='Dashboard'>
            <Content>

                <div className="row">
                    <div className="col-md-3">
                        <div className="card p-30">
                            <div className="media">
                                <div className="media-left meida media-middle">
                                    <span><i className="fa fa-github f-s-40 color-primary"></i></span>
                                </div>
                                <div className="media-body media-text-right">
                                    <h2>28</h2>
                                    <p className="m-b-0">Languages </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="card p-30">
                            <div className="media">
                                <div className="media-left meida media-middle">
                                    <span><i className="fa fa-users f-s-40 color-success"></i></span>
                                </div>
                                <div className="media-body media-text-right">
                                    <h2>1178</h2>
                                    <p className="m-b-0">Authors</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="card p-30">
                            <div className="media">
                                <div className="media-left meida media-middle">
                                    <span><i className="fa fa-book f-s-40 color-warning"></i></span>
                                </div>
                                <div className="media-body media-text-right">
                                    <h2>25</h2>
                                    <p className="m-b-0">Books</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="card p-30">
                            <div className="media">
                                <div className="media-left meida media-middle">
                                    <span><i className="fa fa-reddit-alien f-s-40 color-danger"></i></span>
                                </div>
                                <div className="media-body media-text-right">
                                    <h2>847</h2>
                                    <p className="m-b-0">Bartolos</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </Content>
        </Page>
    </MainLayout>
)

export default DashboardPage
