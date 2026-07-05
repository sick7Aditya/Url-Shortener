import axios from "axios";

const Backend = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true
});

export function PostMapping(url)
{
    return Backend.post("/api/add",{
        email : localStorage.getItem("email"),
        url : url
    });
}
export function traverseUsingCode(code)
{
    Backend.get("/api/show/code");
}

export function MyAllUrls()
{
    return Backend.get("/api/allUrls" ,{
            params : {   //requestbody use ki thi pehle 🥀(GET ke pass RequestBody nhi hoti :3).
                email : localStorage.getItem("email")
            }
        }
    );
}