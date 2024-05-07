import axiosInstance from '@utils/httpCommons'

import API from './../../constants/api'

export const getTeacherList = async () => {
  const response = await axiosInstance.get(
    `${API.BASE_URL}/${API.GET_TEACHER_LIST}`,
  )

  return response
}
