import { HttpResponse, http } from 'msw'

export const handlers = [
  http.get('/posts', () => {
    console.log('Captured a "GET /posts" request')
    return HttpResponse.json({
      message: 'posts mocking success',
    })
  }),
]
