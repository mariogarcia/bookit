import React from 'react'

import { Page, Content } from '../../components/page'
import MainLayout from '../../layouts/MainLayout'

/**
 * Shows a list of Bartolos
 *
 * @since 0.1.0
 */
const BartoloListPage = (props) => (
    <MainLayout>
        <Page title='Bartolos'>
            <Content>
                <div className="card">
                    <div className="card-body"> List of Bartolos. </div>
                </div>
            </Content>
        </Page>
    </MainLayout>
)

export default BartoloListPage
