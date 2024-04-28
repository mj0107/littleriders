import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewShuttle } from '@apis/shuttle'

interface Shuttle {
  licenseNumber: string
  type: string
  name: string
  image: File | null
}
export const useAddNewShuttle = () => {
  const queryClient = useQueryClient()
  const { mutate: addNewShuttle, ...rest } = useMutation({
    mutationFn: ({ licenseNumber, type, name, image }: Shuttle) => {
      const formData = new FormData()
      formData.append('licenseNumber', licenseNumber)
      formData.append('type', type)
      formData.append('name', name)
      if (image) formData.append('image', image)
      return postNewShuttle(formData)
    },

    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getShuttleList'] })
    },
  })

  return { addNewShuttle, ...rest }
}
