import React from 'react';
import SelectUserScreen from '../components/User/SelectUserScreen';
import renderer from 'react-test-renderer';
import {MemoryRouter} from "react-router-dom";

it ("renders correctly", ()=>{
    const tree = renderer.create(
        <SelectUserScreen  location={
            {
                query: {
                    invitedUsers: 'Martin,Can'
                }
            }
        }
        />
    ).toJSON();
    expect(tree).toMatchSnapshot();
});