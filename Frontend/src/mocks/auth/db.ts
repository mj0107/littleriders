import { SignUpInfo } from '@types'

type UserInfo = SignUpInfo

const userList: UserInfo[] = []

export const addUser = (userInfo: UserInfo) => {
  userList.push(userInfo)
}

export const clearUserList = () => {
  userList.length = 0
}

export const getUser = (email: string, password: string) => {
  return userList.find(
    (user) => user.email === email && user.password === password,
  )
}
