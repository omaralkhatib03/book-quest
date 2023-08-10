import {object, string} from "yup";


export const loginSchema = object({
    email: string().email().required("Email ID is required"),
    password: string().min(2).max(20).required("Enter your password"),
});
