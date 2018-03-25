import React from 'react'

import { Page, Content } from '../../components/page'
import MainLayout from '../../layouts/MainLayout'

/**
 * Shows a list of Authors
 *
 * @since 0.1.0
 */
const AuthorListPage = (props) => (
    <MainLayout>
        <Page title='Authors'>
            <Content>
                <div className="card">
                    <div className="card-body"> List of Authors. </div>
                </div>
            </Content>
        </Page>
    </MainLayout>
)

export default AuthorListPage
