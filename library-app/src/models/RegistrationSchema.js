import {object, ref, string} from "yup";


export const registrationSchema = object({
    email: string().email().required("Email ID is required"),
    username: string().min(2).max(20).required("Username is required"),
    password: string().min(2).max(20).required("Enter your password"),
    confirmPassword: string().min(2).max(20).required("Confirm Your password")
    .oneOf([ref("password"), null], "Password must match"),
});