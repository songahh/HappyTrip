import { localAxios } from "@/utils/http-commons";

const local = localAxios();

async function userLogin(success, fail){
  await local.get(`/auth/login`).then(success).catch(fail);
}


async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function registerUser(user, success, fail) {
  // await local.post(`/user/register`, user).then(success).catch(fail);
  //await local.get(`/oauth2/authorization/google`).then(success).catch(fail);
  await local.get(`/auth/register/google`).then(success).catch(fail);
}

async function modifyUser(user, success, fail) {
  await local.patch(`/user/modify`, user).then(success).catch(fail);
}

async function deleteUser(userid, success, fail) {
  await local.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { userLogin, userConfirm, modifyUser, deleteUser, findById, tokenRegeneration, registerUser, logout };
