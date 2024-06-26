import { expect, test } from 'vitest'
import apiServices from '@/scripts/apiServices.js'

test('Status of get request should be 200', async () => {
  const response = await apiServices.getCount()
  expect(response.status).toBe(200)
})

test('Status of get request should be 200', async () => {
  const response = await apiServices.get()
  expect(response.status).toBe(200)
})

//YOU NEED TO MAKE SURE YOU DO NOT HAVE A FOR TESTING TODO IN YOUR
//DATABASE IN ORDER FOR THIS TEST TO WORK
test('Status of Post request should be 201', async () => {
  const status = await apiServices.create('for testing')
  expect(status).toBe(201)
})

//YOU NEED TO ENTER THE ID WHICH THE FOR TESTING TODO WILL BECOME
//IN ORDER FOR THIS TWO TESTS TO WORK
test('Status of put request should be 202', async () => {
  const status = await apiServices.update(240, 'for testing')
  expect(status).toBe(202)
})

test('Status of put request should be 202', async () => {
  const status = await apiServices.delete(240)
  expect(status).toBe(202)
})
