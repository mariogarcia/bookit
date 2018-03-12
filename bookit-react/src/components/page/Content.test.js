import React from 'react';

import Content from './Content'
import { mount, configure } from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

configure({ adapter: new Adapter() });

const wrapper = mount(
    <Content>
        <div className='important-content'>Hi</div>
    </Content>
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
        const div = wrapper.find('div[className="important-content"]')

        it('should render the important div', () => {
            expect(div).toHaveLength(1)
        })

        it('should contain a greetings message', () => {
            expect(div.text()).toEqual('Hi')
        })
    })
})
