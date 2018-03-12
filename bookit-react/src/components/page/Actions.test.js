import React from 'react';

import Actions from './Actions'
import { mount, configure } from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

configure({ adapter: new Adapter() });

const wrapper = mount(
    <Actions>
        <a href='/books/add'>Add book</a>
    </Actions>
)

describe('Content: Rendering page content', () => {

    describe('checking structure', () => {
        it('should have the expected divs and div classes', () => {
            const row = wrapper.find('div[className="row"]')
            const col = wrapper.find('div[className="col-12"]')

            expect(row).toHaveLength(1)
            expect(col).toHaveLength(1)
        })
    })

    describe('checking inner content rendering', () => {
        it('should render the link', () => {
            const anchor = wrapper.find('a')

            expect(anchor).toHaveLength(1)
        })
    })
})
