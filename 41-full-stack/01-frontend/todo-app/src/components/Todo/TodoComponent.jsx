import { useParams } from "react-router-dom"
import { retrieveTodoApi } from "./api/TodoApiService"
import { useAuth } from "./Security/AuthContext"
import { useEffect, useState } from "react"
import {Formik, Form, Field} from 'formik'

export default function TodoComponent(){

    const {id} = useParams()
    const authContext=useAuth()
    const username = authContext.username
    const [description , setDescription] = useState('')
    const [targetDate , setTargetDate] = useState('')
    useEffect(
        ()=>retrieveTodos(), [id] //id가 바뀔때마다 실행
    )

    function retrieveTodos(){
        retrieveTodoApi(username, id)
        .then(
            (response) => {
                setDescription(response.data.description)
                setTargetDate(response.data.targetDate)
            }
        )
        .catch(error=>console.log(error))
    }
    function onSubmit(values){
        console.log(values)
    }

    return (
        <div className="container">
            <h1>Enter Todo Details</h1>
            <div>
            {/* jsx를 리턴하는 함수를 정의한다. */}
            {/* 처음 랜더링할때 값 초기화 */}
                <Formik initialValues={{description, targetDate}} enableReinitialize="true" onSubmit={onSubmit}> 
                    {
                        (props) => (
                            <Form>
                                <fieldset className="form-group">
                                    <label>Description</label>
                                    <Field type="text" className="form-control" name="description"/>
                                </fieldset>   
                                <fieldset className="form-group">
                                    <label>Target Date</label>
                                    <Field type ="date" className="form-control" name="targetDate"/>
                                </fieldset>   
                                <div>
                                    <button className="btn btn-success" type="submit">Save</button>    
                                </div> 
                            </Form>
                            
                        )
                    }
                </Formik>
            </div>
        </div>
    )
}