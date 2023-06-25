import { useNavigate, useParams } from "react-router-dom"
import { createTodoApi, retrieveTodoApi, updateTodoApi } from "./api/TodoApiService"
import { useAuth } from "./Security/AuthContext"
import { useEffect, useState } from "react"
import {Formik, Form, Field, ErrorMessage} from 'formik'


export default function TodoComponent(){

    const {id} = useParams()
    const authContext=useAuth()
    const username = authContext.username
    const [description , setDescription] = useState('')
    const [targetDate , setTargetDate] = useState('')
    const navigate = useNavigate()
    useEffect(
        ()=>retrieveTodos(), [id] //id가 바뀔때마다 실행
    )

    function retrieveTodos(){
        if(id != -1){
            retrieveTodoApi(username, id)
            .then(
                (response) => {
                    setDescription(response.data.description)
                    setTargetDate(response.data.targetDate)
                }
            )
            .catch(error=>console.log(error))
        }
        
    }

    function onSubmit(values){

        console.log(values)
        const todo = {
            id : id,
            username : username,
            description : values.description,
            targetDate : values.targetDate,
            done : false
        }
        if(id == -1){
            createTodoApi(username, todo).then(navigate('/todos')).catch((error)=>console.log(error))
        }else{
            updateTodoApi(username, id, todo).then(navigate('/todos')).catch((error)=>console.log(error))
        }

        
    }

    function validate(values){ //submit 보다 valid가 먼저 호출된다.
        let errors = {}
        if(values.description.length < 5){
            errors.description = '5자 이상 쓰세요'
        }

        if(values.targetDate == null || values.targetDate ===''){
            errors.targetDate = 'Enter a target date' 
        }
        return errors;
    }

    return (
        <div className="container">
            <h1>Enter Todo Details</h1>
            <div>
            {/* jsx를 리턴하는 함수를 정의한다. */}
            {/* 처음 랜더링할때 값 초기화 */}
                <Formik 
                     initialValues={{description, targetDate}} 
                    enableReinitialize="true" 
                    onSubmit={onSubmit} 
                    validate={validate}
                    validateOnChange={false} 
                    validateOnBlur={false} 
                 > 
                 {/* 입력 하는 동안 검증되는걸 방지 */}
                    {
                        (props) => (
                            <Form>
                                <ErrorMessage 
                                    name="description" 
                                    component="div"
                                    className="alert alert-warning"
                                />
                                <ErrorMessage 
                                    name="targetDate" 
                                    component="div"
                                    className="alert alert-warning"
                                />
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