import React from 'react'

import { Page, Content } from '../../components/page'
import MainLayout from '../../layouts/MainLayout'

/**
 * Shows a list of links
 *
 * @since 0.1.0
 */
export const LinkListPage = (props) => (
    <MainLayout>
        <Page title='Links'>
            <Content>
                <div className="card">
                    <div className="card-body"> List of links. </div>
                </div>
            </Content>
        </Page>
    </MainLayout>
)
