import { Navigate, Outlet } from "react-router-dom";

const useAuth = () => {
    if(localStorage.getItem("info")){
        return true;
    }
    return false;
};

const ProtectedRoutes = () => {
  const isAuth = useAuth();
  return isAuth ? <Outlet /> : <Navigate to="/login" />;
};

export default ProtectedRoutes;