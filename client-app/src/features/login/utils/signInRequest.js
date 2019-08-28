import { useEffect, useState } from "react";
import axios from "axios";

export default function SignInUser() {
  const [res, setRes] = useState("");

  useEffect(() => {
    async function postData() {
      try {
        const res = await axios.post("http://localhost:8080/api/signin", {
          username: "abc1",
          password: "abc123"
        });
        console.log("response-------->", res);
        setRes(res.data.token);
      } catch (error) {
        console.error(error);
      }
    }
    postData();
  }, []);

  // Render as usual
  return res;
}
