import axios from "axios";

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create(
    {
        baseURL : "http://localhost:8080"
    }
)

//이렇게도 사용할수있다.
export const retrieveHelloWorldBean = () => apiClient.get('/hello-world-bean')

export const retrieveHelloWorldPathVariable = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`, {
    headers: {
        // Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource. => 인증 header 때문에 생긴 에러 (프리플라이트 요청이 엑세스 제어 체크를 통과하지 못하는중)
        // 프리플라이트 요청 = option 요청 이 먼저 요청되고 , 그 후에 실제 요청이 전송된다.
        // 1. 모든 사람에게 option 요청에 대한 엑세스를 허용해 줘야 한다.
        // 2. 로그인 시점에 백엔드에 테스트 요청을 보내야한다. (토큰을 생성한다.)
        // 3. rest api를 요청할 때마다 해당 토큰을 사용한다.
        Authorization : token //인코딩된 사용자 id / password
    }
})


export const executeBasicAuthenticationService = (token) => apiClient.get(`/basicauth`, {
    headers: {
        Authorization : token
    }
})