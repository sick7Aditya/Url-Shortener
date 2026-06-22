import axios from "axios";

const Backend = axios.create({
    baseURL : "http://localhost:8080"
})

export function PostMapping(url)
{
    return Backend.post("/api/add",{
        url : url
    });
}
export function traverseUsingCode(code)
{
    Backend.get("/api/show/code");
}

