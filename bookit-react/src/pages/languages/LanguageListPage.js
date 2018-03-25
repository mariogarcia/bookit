import React from 'react'

import { Page, Content } from '../../components/page'
import MainLayout from '../../layouts/MainLayout'

/**
 * Shows a list of Languages
 *
 * @since 0.1.0
 */
const LanguageListPage = (props) => (
    <MainLayout>
        <Page title='Languages'>
            <Content>
                <div className="card">
                    <div className="card-body"> List of Languages. </div>
                </div>
            </Content>
        </Page>
    </MainLayout>
)

export default LanguageListPage
